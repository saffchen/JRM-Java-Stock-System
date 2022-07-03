FROM node:lts-slim

WORKDIR /app
COPY package*.json ./
RUN npm install && apt-get update && apt-get install -y wget

COPY ./ .
ENTRYPOINT ["npm", "run", "serve"]
