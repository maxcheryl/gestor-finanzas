# Plan de Pruebas - Gestor de Finanzas Personales

## Objetivo

Validar el correcto funcionamiento de los microservicios del sistema mediante pruebas unitarias.

## Herramientas

* Spring Boot
* JUnit 5
* DataFaker

---

# Microservicio de Categorías

## Objetivo del módulo

Validar el funcionamiento de las clases Categoria y CategoriaPersonalizada.

### CAT-01
Crear categoría.

#### Objetivo
Verificar que una categoría pueda crearse correctamente.

#### Resultado esperado
* Nombre válido.
* Tipo válido.

### CAT-02
Crear categoría personalizada.

#### Objetivo
Verificar que una categoría personalizada pueda crearse correctamente.

#### Resultado esperado
* Nombre válido.
* Tipo válido.

---

# Microservicio de Compras

## Objetivo del módulo

Validar el funcionamiento de la clase CompraFutura.

### COM-01
Crear compra futura.

#### Objetivo
Verificar que una compra futura pueda crearse correctamente.

#### Resultado esperado
* Usuario asociado.
* Producto válido.
* Precio mayor a cero.

---

# Microservicio de Cuentas Financieras

## Objetivo del módulo

Validar el funcionamiento de las clases EntiFinan y MetodoPago.

### CTA-01
Crear entidad financiera.

#### Objetivo
Verificar que una entidad financiera pueda crearse correctamente.

#### Resultado esperado
* Usuario asociado.
* Nombre válido.
* Método de pago asociado.

### CTA-02
Crear método de pago.

#### Objetivo
Verificar que un método de pago pueda crearse correctamente.

#### Resultado esperado
* Nombre válido.

---

# Microservicio de Gastos

## Objetivo del módulo

Validar el funcionamiento de las clases Gasto y GastoRecurrente.

### GAS-01
Crear gasto.

#### Objetivo
Verificar que un gasto pueda crearse correctamente.

#### Resultado esperado
* Monto válido.
* Fecha válida.
* Categoría asociada.

### GAS-02
Crear gasto recurrente.

#### Objetivo
Verificar que un gasto recurrente pueda crearse correctamente.

#### Resultado esperado
* Monto válido.
* Descripción válida.

---

# Microservicio de Historial de Ahorro

## Objetivo del módulo

Validar el funcionamiento de la clase AhorroAcumulado.

### HA-01
Crear ahorro acumulado.

#### Objetivo
Verificar que un ahorro acumulado pueda crearse correctamente.

#### Resultado esperado
* Usuario asociado.
* Meta asociada.
* Saldo válido.

---

# Microservicio de Ingresos

## Objetivo del módulo

Validar el funcionamiento de las clases IngresoFijo e IngresoExtra.

### ING-01
Crear ingreso fijo.

#### Objetivo
Verificar que un ingreso fijo pueda crearse correctamente.

#### Resultado esperado
* Monto válido.
* Entidad financiera asociada.

### ING-02
Crear ingreso extra.

#### Objetivo
Verificar que un ingreso extra pueda crearse correctamente.

#### Resultado esperado
* Monto válido.
* Descripción válida.

---

# Microservicio de Metas

## Objetivo del módulo

Validar el funcionamiento de la clase MetaMensual.

### MET-01
Crear meta mensual.

#### Objetivo
Verificar que una meta mensual pueda crearse correctamente.

#### Resultado esperado
* Usuario asociado.
* Cuota mensual válida.

---

# Microservicio de Presupuestos

## Objetivo del módulo

Validar el funcionamiento de la clase PresupuestoMen.

### PRE-01
Crear presupuesto mensual.

#### Objetivo
Verificar que un presupuesto mensual pueda crearse correctamente.

#### Resultado esperado
* Mes válido.
* Monto inicial válido.
* Monto disponible válido.

---

# Microservicio de Reportes

## Objetivo del módulo

Validar el funcionamiento de las clases ReporteMensual y DetalleReporte.

### REP-01
Crear reporte mensual.

#### Objetivo
Verificar que un reporte mensual pueda crearse correctamente.

#### Resultado esperado
* Ingresos válidos.
* Gastos válidos.
* Saldo neto válido.

### REP-02
Asociar detalle a reporte.

#### Objetivo
Verificar la relación entre reporte y detalle.

#### Resultado esperado
* Asociación correcta.
* Integridad de datos.

---

# Microservicio de Usuarios

## Objetivo del módulo

Validar el funcionamiento de la clase Usuario.

### USR-01
Crear usuario.

#### Objetivo
Verificar que un usuario pueda crearse correctamente.

#### Resultado esperado
* Nombre válido.
* Apellidos válidos.

### USR-02
Generar nombre completo.

#### Objetivo
Verificar la composición correcta del nombre completo.

#### Resultado esperado
* Nombre completo generado correctamente.