FROM node:18-alpine
WORKDIR /home/cab-frontend

COPY package.json /home/cab-frontend/
RUN npm install

COPY public/ /home/cab-frontend/public
COPY src/ /home/cab-frontend/src

CMD ["npm", "start"]
