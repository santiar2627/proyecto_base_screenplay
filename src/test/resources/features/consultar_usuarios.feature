Feature: Consulta de usuarios sin API key
  Como consumidor de la API
  Quiero consultar /api/users sin API key
  Para validar que el servicio responde 401 con un mensaje de error claro

   @negativo @usuarios
  Scenario: GET /api/users sin API key retorna 401
    Given que el actor de API está configurado contra la base URL
    When consulta la lista de usuarios sin api key
    Then la respuesta HTTP debe ser 401
    And el campo "error" debe ser "Missing API key"

  @api @positivo @usuarios
  Scenario: GET /api/users retorna 200
    Given que el actor de API está configurado contra la base URL
    When consulta la lista de usuarios
    Then la respuesta HTTP debe ser 200
    And el usuario con id 1 debe tener los siguientes datos
      | email      | george.bluth@reqres.in                  |
      | first_name | George                                  |
      | last_name  | Bluth                                   |
      | avatar     | https://reqres.in/img/faces/1-image.jpg |