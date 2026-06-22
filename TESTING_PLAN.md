## Pruebas Unitarias y Cobertura de Reglas de Negocio
Este documento resume las reglas de negocio críticas del microservicio de Subpedido y el estado actual de cobertura mediante pruebas unitarias.
El microservicio fue probado en cuatro capas principales: modelo, servicio, repositorio y controlador.

### Reglas de Negocio Críticas del Servicio de Productos
1. Creación de Subpedido: Todo subpedido generado debe iniciar con estado PENDIENTE.
2. Consulta de Subpedido: Solo se pueden obtener subpedidos existentes. Si el identificador no existe, debe lanzarse una excepción.
3. Asignación de Stand: Un subpedido existente puede asociarse a un stand específico.
4. Actualización de Estado: El estado del subpedido debe poder modificarse durante su ciclo de vida (por ejemplo: PENDIENTE, PREPARANDO, ENTREGADO).
5. Búsqueda por Stand: Debe ser posible consultar todos los subpedidos asociados a un stand.

### Cobertura Actual
| Regla                  | Estado          | Casos Cubiertos                              |
|------------------------|-----------------|----------------------------------------------|
| 1. Creacion de Subpedido | ✅ Cubierta   | Generación automática del Subpedido          |
| 2. Consulta de Subpedido | ✅ Cubierta   |Búsqueda exitosa por ID, excepción cuando no existe  |
| 3. Asignacion de Stand  | ✅ Cubierta    | Asignación correcta evitando conflictos entre stand |
| 4.Actualizacion de Estado| ✅ Cubierta   | Cambio de estado segun conveniencia           |
| 5.Búsqueda por Stand    |  ✅ Cubierta   | Consulta mediante findByStandId()             |

### Cobertura por Capa
|Capa	|Tipo de Prueba|	Estado|
|------|---------------|--------|
|Controller	|Pruebas de endpoints REST con MockMvc|	✅
|Service|	Pruebas unitarias con Mockito	|✅
|Repository	|Pruebas de persistencia con DataJpaTest|	✅
|Model|	Validación de getters y setters|	✅

## Comando de Prueba de Test
mvn clean test

### Reflexión y Deuda Técnica
- Acción Futura: Incorporar pruebas de integración entre microservicios utilizando contenedores o mocks avanzados.
