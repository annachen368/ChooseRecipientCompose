# Domain

## Domain Models(Core Business Logic)
- Models that represent core business objects independent of data source (e.g., User, Product, Order).
- These models are used in the domain layer, typically in use-cases or interactors.
- No framework or library-specific annotations (like Room @Entity or Gson @SerializedName).
- Should not care about whether the data comes from API, Database, etc.

## Domain Use Cases