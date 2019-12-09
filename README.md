# Drools Rule Engine

A Simple Rule Engine Built over Drools. Normal REST POST calls are made to update and create the rules. Rules can be executed via REST POST Calls as well.

Rules are kept into different shards. 10 knowledge shards are used to have faster performance while saving new Rules.
Rules are also saved to DB so when we redeploy/re-run the project rules can be reloaded into Drools knowledgebase.

Apache Velocity Template Engine is used to convert the JSON rules into Drools Rules.  

## Installation

Being a spring boot project, All we need is to resolve all the dependencies. Also setup a MySQL DB to persist the rules.
Look into application.properties for DB setup

## Usage

Add A Rule :
```
{
	"id" : 1,
	"ruleName" : "SBI",
	"discountAmount" : "10",
	"price" : 10000
}
```

Add An Item :
```
{
	"name" : "Mobile",
	"cardType" : "SBI",
	"price" : 11000
}
```

Execute Rules :
```
{
    "name": "Mobile",
    "cardType": "SBI",
    "discount": 0,
    "price": 11000,
    "id": 2
}
```

## Load Test The Rule Engine

I have created a k6.js Script for creating thousands of rules. Script is as Follows

```
import http from "k6/http";
import { sleep } from "k6";

export default function() {
  for(var i = 1001; i < 10000; i++) {
    var url = "http://localhost:8080/offer";
    var payload = JSON.stringify({
      id : i,
	    ruleName : Math.random() >= 0.5 ? "SBI" : "HDFC",
	    discountAmount : "10",
	    price : 1000+(i*100)
    });
    var params =  { headers: { "Content-Type": "application/json" } }
    http.post(url, payload, params);
    sleep(1);
  }
};
```


## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## Future Plans

Get A better Sharding Strategy for better performance.
