Drop Table if exists PostOfficeData;
Drop Table if exists Data;

Create Table PostOfficeData(
    idPostOfficeData INTEGER PRIMARY KEY,
    nrPostMen INTEGER NOT NULL,
    nrEncomendas INTEGER NOT NULL
);

Create Table Data(
    idData INTEGER PRIMARY KEY,
    nrOrders INTEGER NOT NULL,
    nrPostMen INTEGER NOT NULL,
    idOrder INTEGER NOT NULL,
    idPostMan INTEGER NOT NULL,
    maxLOad INTEGER NOT NULL,
    currentLoad INTEGER NOT NULL,
    personalGain REAL NOT NULL,
    distance REAL NOT NULL,
    estimatedTime INTEGER NOT NULL,
    proposal REAL NOT NULL,
    decision INTEGER
);
