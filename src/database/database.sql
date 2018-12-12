Drop Table if exists PostMan;
Drop Table if exists PostOfficeData;
Drop Table if exists Data;

Create Table PostMan(
    idPostMan INTEGER PRIMARY KEY,
    name varchar(56)
);

Create Table PostOfficeData(
    idPostOfficeData INTEGER PRIMARY KEY,
    nrPostMen INTEGER NOT NULL,
    nrOrders INTEGER NOT NULL
);

Create Table Data(
    idData INTEGER PRIMARY KEY,
    nrOrders INTEGER NOT NULL,
    nrPostMen INTEGER NOT NULL,
    idOrder INTEGER NOT NULL,
    idPostMan INTEGER NOT NULL,
    maxLOad INTEGER NOT NULL,
    currentLoad INTEGER NOT NULL,
    activeOrders INTEGER NOT NULL,
    personalGain REAL NOT NULL,
    distance REAL NOT NULL,
    goingToPostOffice INTEGER NOT NULL,
    estimatedTime INTEGER NOT NULL,
    proposal REAL NOT NULL,
    decision INTEGER
);

-- SELECT DISTINCT max(idPostMan) as maxId FROM Data;

-- INSERT INTO PostOfficeData (nrPostMen, nrEncomendas) VALUES (2,2);
-- INSERT INTO PostOfficeData (nrPostMen, nrEncomendas) VALUES (2,3);
--
--
-- SELECT nrPostMen, nrEncomendas FROM PostOfficeData ORDER BY idPostOfficeData DESC LIMIT 1;

-- INSERT INTO Data(nrOrders,nrPostMen,idOrder,idPostMan,maxLOad,currentLoad,personalGain,distance,estimatedTime,proposal) VALUES
-- (2,1,2,1,10,4,1.2,12,10,15)

-- INSERT INTO Data(nrOrders,nrPostMen,idOrder,idPostMan,maxLOad,currentLoad,personalGain,distance,estimatedTime,proposal) VALUES (2,1,2,1,10,4,1.2,12,10,15);
