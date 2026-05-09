
**GraphQL is use to optimize the JSON payload by choosing only specific node to retrieve, update, create and delete.
instead to put all the keys and value on the JSON payload, GraphQL filter only needed keys and values on the payload.

Types of GraphQL:
* Mutations - Create, Update, Delete data JSON payload
* Query - Retrieve data JSON payload
* Subscription - unknown

To convert GraphQL into JSON format:
Step 1
- Inspect element then goto Network Tab
- Make another request then under "Name" find "graphql" file
- go to Payload then copy the JSON payload
Examples,
{"query":"mutation {\n  createLocation(location: {name: \"scott\", type: \"PH\", dimension: \"234\"}) {\n    id\n  }\n  \n  createCharacter(character: {name: \"byron\", type: \"Manila\", status: \"single\", species: \"Human\", gender: \"Male\", image: \".png\", originId: 30820, locationId: 30820} ){\n    \n    id\n  }\n  \n  createEpisode(episode: {name: \"One Piece\", air_date: \"01/01/1990\", episode: \"Egghead Arc\"}){\n    \n    id\n  }\n}\n","variables":null}

- then you can now paste this on the .body()


Note:
*this commonly use by Post method only.






