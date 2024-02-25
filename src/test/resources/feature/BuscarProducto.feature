Feature: Buscar Producto

  @PRUEBA1
  Scenario: Buscar Productos notebook
    Given ingreso a la pagina web de mercado libre "https://www.mercadolibre.cl"
    When ingreso el texto "notebook"
    And le doy click al boton buscar
    Then Valido que me muestre el valor "Ordenar por"