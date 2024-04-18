FROM node AS build

WORKDIR /dbpautas-front-end

COPY package.json .
COPY package-lock.json .

RUN ["npm", "ci"]

COPY . .

RUN ["npm", "run", "build"]

FROM nginx

COPY --from=build /dbpautas-front-end/dist /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d

EXPOSE 80