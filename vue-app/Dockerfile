FROM node:lts-slim

WORKDIR /app
COPY package*.json /app
RUN npm install && apt-get update && apt-get install -y wget
VOLUME /app

ENTRYPOINT ["npm", "run", "serve"]
