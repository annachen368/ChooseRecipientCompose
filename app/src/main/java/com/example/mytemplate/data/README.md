# Data

## Data Api

## Data Local Models(DTOs, Entities, etc)
- Models used for data operations, such as: Database entities (Room Entities).
- May include annotations like @Entity, @PrimaryKey.
- Not used outside the data layer.

## Data Remote Models(DTOs, Entities, etc)
- Models used for data operations, such as: API responses (DTOs). 
- These models are tied to specific data sources and often require mapping to/from domain models.
- May include annotations like @SerializedName, @Entity, @PrimaryKey.
- Not used outside the data layer.

## Data Repository