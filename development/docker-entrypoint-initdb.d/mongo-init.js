print('Start #################################################################');

db = db.getSiblingDB('inventorymanger');
db.createUser(
    {
        user: 'steve',
        pwd: 'password',
        roles: [{ role: 'readWrite', db: 'inventorymanger' }],
    },
);
db.createCollection('users');

db = db.getSiblingDB('menumanger');
db.createUser(
    {
        user: 'steve',
        pwd: 'password',
        roles: [{ role: 'readWrite', db: 'menumanger' }],
    },
);
db.createCollection('users');

db = db.getSiblingDB('kitchenmanger');
db.createUser(
    {
        user: 'steve',
        pwd: 'password',
        roles: [{ role: 'readWrite', db: 'kitchenmanger' }],
    },
);
db.createCollection('users');

db = db.getSiblingDB('overviewmanger');
db.createUser(
    {
        user: 'steve',
        pwd: 'password',
        roles: [{ role: 'readWrite', db: 'overviewmanger' }],
    },
);
db.createCollection('users');

print('END #################################################################');