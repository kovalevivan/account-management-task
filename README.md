# Account management service

Implemented as a part of interview process.

# API

### Money transfer endpoint

Path:

```
/transfer
```

Example request:

```
{
    "requestId": "adfkn34fd",
    "accountFrom": "a7ed27c5-218f-4246-8ba4-aab107c13b4e",
    "accountTo": "242de9fc-6672-4f42-b4c8-cc2ae30a6af1",
    "value": "100.31"
}
```

### Account details endpoint

Path:

```
/account/{accountId}
```

Example response:

```
{
    "id": "a7ed27c5-218f-4246-8ba4-aab107c13b4e",
    "balance": "100.31",
    "updatedDate": "2022-06-01 14:00",
    "createdDate": "2022-06-01 14:00"
}
```