# ---- Base Node ----
FROM node:10.2.0-alpine AS build

# set working directory
WORKDIR /app

# copy project file
COPY package.json package-lock.json ./
 
# install node packages
RUN npm set progress=false && npm config set depth 0
RUN npm install --only=production 
# copy production node_modules aside
RUN cp -R node_modules /tmp/prod_node_modules
# install ALL node_modules, including 'devDependencies'
RUN npm install
 
#
# ---- Test ----
# run linters, setup and tests
FROM build AS test
COPY . .
RUN npm run test
 
#
# ---- Release ----
FROM build AS release
# copy production node_modules
COPY --from=build /tmp/prod_node_modules ./node_modules
# copy app sources
COPY . .
# expose port and define CMD
EXPOSE 80
CMD ["npm", "run", "start"]