FROM mcr.microsoft.com/dotnet/core/sdk:3.1.201-buster AS build

# Add in debugging binaries
SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN apt-get update && \
  apt-get install --no-install-recommends -y unzip && \
  rm -rf /var/lib/apt/lists/* && \
  curl -sSL https://aka.ms/getvsdbgsh | /bin/sh /dev/stdin -v latest -l /vsdbg && \
  dotnet tool install -g dotnet-format --version 4.0.130203


WORKDIR /app

# Copy csproj and restore as distinct layers
COPY *.csproj ./
RUN dotnet restore

# Copy everything else and build
COPY . ./
RUN dotnet publish -c Release -o out

# Build runtime image
FROM mcr.microsoft.com/dotnet/core/aspnet:3.1.0-alpine
WORKDIR /app
COPY --from=build /app/out .

EXPOSE 80

ENTRYPOINT ["dotnet", "dotnetcore.dll"]