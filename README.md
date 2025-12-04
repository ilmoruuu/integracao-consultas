# integracao-consultas

rabbitmq:
Resumo do Fluxo
1. Usuário chama DELETE /consulta/1.
2. Service: Busca a consulta, monta o DTO de mensagem.
3. Producer: Joga o DTO na fila consulta.cancelada.fila.
4. Service: Apaga a consulta do banco de dados e retorna 204 No Content.
5. Listener (em paralelo): Percebe que chegou mensagem na fila, lê o JSON e imprime no console ("Enviando e-mail...").