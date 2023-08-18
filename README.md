# Social-Network
API
Registering user
curl --location --request POST 'http://localhost:8080/api/user' \
--header 'Content-Type: application/json' \
--data-raw '{
"id":2,
"username":"Prakash"
}'

Api for registering group
curl --location --request POST 'http://localhost:8080/api/group' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 1,
"name": "Friends",
"members": [
{
"id": 2,
"username": "Prakash"
}
]
}'

Api for post
curl --location --request POST 'http://localhost:8080/api/group/1/posts?userId=1' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 1,
"content": "Hi Vipin"
}'

Api for fetching group
curl --location --request GET 'http://localhost:8080/api/group/1/posts' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 1,
"content": "Hello frineds this is my first post"
}'


Api for feed
curl --location --request GET 'http://localhost:8080/api/feed?userId=1' \
--header 'Content-Type: application/json' \
--data-raw ''

Api for like
curl --location --request POST 'http://localhost:8080/api/post/1/like?userId=1' \
--header 'Content-Type: application/json' \
--data-raw ''


Api for comment
curl --location --request POST 'http://localhost:8080/api/post/1/comment?userId=1' \
--header 'Content-Type: application/json' \
--data-raw '{
"id":2,
"content":"Hi there its Prakash here",
"author":{
"id":2,
"username":"Prakash"
}
}'
