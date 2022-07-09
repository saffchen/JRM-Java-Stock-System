FROM node:lts-slim

WORKDIR /app
COPY package.json /app/
RUN npm install --location=global @vue/cli@5.0.6 && npm install
COPY ./ /app/

ENTRYPOINT ["npm", "run", "serve"]
