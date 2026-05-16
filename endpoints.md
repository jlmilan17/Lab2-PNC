# Endpoints

## Endpoints Existentes 

### Jose Luis
### PersonController (`/api/users`)

| Método | Endpoint | Función |
|--------|----------|---------|
| POST | `/register` | Registrar persona |
| GET | `/charged` | Listar personas con cargos| 
| GET | `/most-wanted` | Listar más buscados |
| GET | `/{dui}/charges` | Ver cargos de una persona |

### ChargesController (`/api/charges`)

| Método | Endpoint | Función |
|--------|----------|---------|
| POST | `/register` | Registrar denuncia/cargo | 
| GET | `/` | Listar todas las denuncias |

---

## Endpoints por Implementar

### Chinchi

#### PersonController (`/api/users`)

| Método | Endpoint | Función |
|--------|----------|---------|
| GET | `/` | Listar todas las personas |
| GET | `/{dui}` | Obtener persona por DUI |
| PUT | `/{dui}` | Actualizar persona |
| DELETE | `/{dui}` | Eliminar persona |

---

### Ivan

#### ChargesController (`/api/charges`)

| Método | Endpoint | Función |
|--------|----------|---------|
| GET | `/{id}` | Obtener denuncia por ID |
| PUT | `/{id}` | Actualizar denuncia |
| DELETE | `/{id}` | Eliminar denuncia |
| PUT | `/{id}/status` | Actualizar estado |

#### PoliceStationController (`/api/police-stations`)

| Método | Endpoint | Función |
|--------|----------|---------|
| GET | `/` | Listar estaciones |

---

## Resumen

| Responsable | Endpoints          |
|-------------|--------------------|
| Jose Luis | 6                  |
| Carlos Ramirez | 4                  |
| Ivan Nolasco | 5                  |

**Total proyectado: 15 endpoints**