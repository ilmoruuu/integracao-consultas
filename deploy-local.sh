#!/bin/bash

# --- CONFIGURAÇÕES BÁSICAS ---
DOCKER_IMAGE_NAME="upebr/consultas"
MS_PORT="8080"
SECRETS_FILE="secrets.env" # Novo arquivo para credenciais locais

# 1. Carregar Secrets Locais
# O script tentará carregar as variáveis de ambiente sensíveis de um arquivo local, NÃO VERSIONADO
if [ -f "$SECRETS_FILE" ]; then
    source "$SECRETS_FILE"
    echo "Secrets carregadas de $SECRETS_FILE."
else
    echo "⚠️ AVISO: Arquivo de secrets locais ($SECRETS_FILE) não encontrado."
    echo "Certifique-se de que RABBITMQ_USER e RABBITMQ_PASS estão definidos no seu ambiente."
fi

# --- FLUXO DE DEPLOY ---

echo "================================================="
echo "INICIANDO DEPLOY LOCAL DO MICROSSERVIÇO CONSULTAS"
echo "================================================="

# 2. Obter o ambiente
ENV_PROFILE="$1"

# Verifica se o ambiente foi fornecido
if [ -z "$ENV_PROFILE" ]; then
    echo "❌ Erro: O ambiente (dev ou prd) deve ser fornecido como primeiro argumento."
    echo "Uso: ./deploy-local.sh [dev | prd]"
    exit 1
fi

# Converte para minúsculas e valida
ENV_PROFILE=$(echo "$ENV_PROFILE" | tr '[:upper:]' '[:lower:]')

if [ "$ENV_PROFILE" != "dev" ] && [ "$ENV_PROFILE" != "prd" ]; then
    echo "❌ Erro: Ambiente inválido. Use 'dev' ou 'prd'."
    exit 1
fi

# 3. Validar se as secrets foram definidas
if [ -z "$RABBITMQ_USER" ] || [ -z "$RABBITMQ_PASS" ]; then
    echo "❌ Erro Crítico: As variáveis RABBITMQ_USER e RABBITMQ_PASS não estão definidas."
    echo "Por favor, defina-as no seu ambiente ou no arquivo $SECRETS_FILE."
    exit 1
fi


# 4. Configurar variáveis baseadas no ambiente
if [ "$ENV_PROFILE" == "prd" ]; then
    # Variáveis de Conexão e Nomes para Produção
    PROJECT_NAME="ms-consultas-prd"
    RABBITMQ_AMQP_PORT="5672"
    RABBITMQ_MGMT_PORT="15672"
    TAG_SUFFIX="" # A tag de PRD é apenas o SHA
    echo "Modo: Produção (PRD)"
else
    # Variáveis de Conexão e Nomes para Desenvolvimento
    PROJECT_NAME="ms-consultas-dev"
    RABBITMQ_AMQP_PORT="5673" 
    RABBITMQ_MGMT_PORT="15673"
    TAG_SUFFIX="-dev" # A tag de DEV tem o sufixo
    echo "Modo: Desenvolvimento (DEV)"
fi

echo "Porta RabbitMQ (AMQP): $RABBITMQ_AMQP_PORT"
echo "Porta RabbitMQ (MGMT): $RABBITMQ_MGMT_PORT"

# 5. Obter o SHA do Commit
read -p "Digite o SHA Curto (7 caracteres) do commit para $ENV_PROFILE (Ex: a1b2c3d): " SHA_CURTO

if [ -z "$SHA_CURTO" ]; then
    echo "❌ Erro: O SHA do commit não pode ser vazio."
    exit 1
fi

DOCKER_IMAGE_TAG="${SHA_CURTO}${TAG_SUFFIX}"
FULL_IMAGE="${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"

echo ""
echo "Configurando variáveis para a tag: $DOCKER_IMAGE_TAG"

# 6. Criar/Atualizar o arquivo .env (Variáveis para o Docker Compose)
cat << EOF > .env
# Variáveis de Configuração do Docker Compose ($ENV_PROFILE Local)
PROJECT_NAME=$PROJECT_NAME
DOCKER_IMAGE_NAME=$DOCKER_IMAGE_NAME
DOCKER_IMAGE_TAG=$DOCKER_IMAGE_TAG
ENV_PROFILE=$ENV_PROFILE

# Configurações de Porta
MS_CONSULTAS_PORT=$MS_PORT
RABBITMQ_AMQP_PORT=$RABBITMQ_AMQP_PORT
RABBITMQ_MGMT_PORT=$RABBITMQ_MGMT_PORT

# Credenciais RabbitMQ (Carregadas do ambiente local/secrets.env)
RABBITMQ_USER=$RABBITMQ_USER
RABBITMQ_PASS=$RABBITMQ_PASS
EOF

echo "Arquivo .env (para Docker Compose) atualizado com perfil $ENV_PROFILE."
echo ""

# 7. Puxar a nova imagem do Docker Hub
echo "Puxando a nova imagem: $FULL_IMAGE do Docker Hub..."
docker compose pull consultas-ms

if [ $? -ne 0 ]; then
    echo "❌ Erro ao puxar a imagem. Verifique se a tag ($DOCKER_IMAGE_TAG) está correta e se a imagem existe no Docker Hub."
    exit 1
fi

# 8. Iniciar/Atualizar o serviço com a nova imagem
echo "⬆️ Iniciando e atualizando o Microsserviço Consultas..."
docker compose up -d --no-deps consultas-ms

if [ $? -ne 0 ]; then
    echo "❌ Erro ao iniciar o serviço. Verifique os logs do container."
    docker compose logs consultas-ms
    exit 1
fi

echo "✅ DEPLOY ($ENV_PROFILE) CONCLUÍDO!"
echo "Microsserviço de Consultas rodando com a tag: $DOCKER_IMAGE_TAG"
echo "Acesse: http://localhost:$MS_PORT"
echo "================================================="