rootProject.name = "Cities"
include("city-api")
include("city-app")
include("integrations")
include("integrations:local-integration")
findProject(":integrations:local-integration")?.name = "local-integration"
