#Integrantes
-Rafael Moreno
- Cristian Gonzalez
# Reto #6: Sistema de Soporte Técnico

## Patrón de Diseño

**Categoría de Patrón de Diseño:** De Comportamiento

**Patrón Utilizado:** Chain of Responsibility

**Justificación:** Se requiere que un ticket de soporte sea evaluado por una cadena de técnicos (Básico → Intermedio → Avanzado). Si un técnico no tiene el nivel para resolverlo, lo delega automáticamente al siguiente nivel.

**Cómo lo aplico:** Implementando una clase abstracta `TecnicoHandler` que mantiene una referencia al siguiente técnico en la cadena y define el método `procesarTicket`. Cada técnico concreto (`TecnicoBasico`, `TecnicoIntermedio`, `TecnicoAvanzado`) extiende esta clase y decide si puede resolver el ticket según su nivel y prioridad, o lo pasa al siguiente en la cadena.

## Descripción del Sistema

Sistema de soporte técnico que procesa tickets de clientes con diferentes niveles de complejidad:
- **Básico:** Problemas simples (prioridad 1)
- **Intermedio:** Problemas moderados (prioridad 1-2)
- **Avanzado:** Problemas complejos (cualquier prioridad 3)

### Reglas de Asignación
- **Técnico Básico:** Resuelve tickets básicos con prioridad 1
- **Técnico Intermedio:** Resuelve tickets intermedios con prioridad ≤ 2
- **Técnico Avanzado:** Resuelve tickets avanzados o cualquier ticket con prioridad 3

### Casos de Tickets Pendientes
Un ticket queda como "Pendiente de escalamiento" cuando ningún técnico puede resolverlo:
- **Ticket básico con prioridad 2 o 3** (Ej: básico + prioridad 2)
- **Ticket intermedio con prioridad 3** (Ej: intermedio + prioridad 3)
- **Nivel inválido** (Ej: nivel "experto" no existe)

## Características Implementadas

 Ingreso de X número de tickets por el usuario  
 Cada ticket tiene descripción, nivel de dificultad y prioridad  
 Cadena de responsabilidad procesa cada ticket  
 Muestra qué técnico resolvió cada ticket  
 Marca tickets como "Pendiente de escalamiento" si nadie puede resolverlos  
 Usa Streams para estadísticas:
  - Conteo de tickets por nivel
  - Tickets resueltos vs pendientes
  - Promedio de prioridad
  - Distribución por técnico

## Estructura del Proyecto

```
src/main/java/com/dosw/reto6/
├── Ticket.java                 # Modelo de datos
├── TecnicoHandler.java         # Clase abstracta (patrón)
├── TecnicoBasico.java          # Handler concreto
├── TecnicoIntermedio.java      # Handler concreto
├── TecnicoAvanzado.java        # Handler concreto
└── Reto6Soporte.java           # Clase principal
```

## Ejecución

```bash
mvn clean compile
mvn exec:java
```

## Ejemplo de Uso

```
¿Cuántos tickets desea ingresar? 3

--- Ticket #1 ---
Descripción: No puedo imprimir
Nivel (basico/intermedio/avanzado): basico
Prioridad (1=baja, 2=media, 3=alta): 1

--- Ticket #2 ---
Descripción: Error en base de datos
Nivel (basico/intermedio/avanzado): avanzado
Prioridad (1=baja, 2=media, 3=alta): 3

--- Ticket #3 ---
Descripción: Configurar red
Nivel (basico/intermedio/avanzado): intermedio
Prioridad (1=baja, 2=media, 3=alta): 2
```

