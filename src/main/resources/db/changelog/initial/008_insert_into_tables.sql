INSERT INTO roles (role_name)
VALUES ('user');

INSERT INTO users (
    password,
    login,
    user_name,
    description,
    followers,
    subscriptions,
    role_name,
    email
)
-- Пароль от всех аккаунтов 1 --
VALUES
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user1','User One','Test user 1',3,2,(SELECT id FROM roles WHERE role_name='user'),'user1@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user2','User Two','Test user 2',3,2,(SELECT id FROM roles WHERE role_name='user'),'user2@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user3','User Three','Test user 3',2,1,(SELECT id FROM roles WHERE role_name='user'),'user3@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user4','User Four','Test user 4',2,1,(SELECT id FROM roles WHERE role_name='user'),'user4@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user5','User Five','Test user 5',2,2,(SELECT id FROM roles WHERE role_name='user'),'user5@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user6','User Six','Test user 6',2,1,(SELECT id FROM roles WHERE role_name='user'),'user6@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user7','User Seven','Test user 7',2,1,(SELECT id FROM roles WHERE role_name='user'),'user7@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user8','User Eight','Test user 8',2,1,(SELECT id FROM roles WHERE role_name='user'),'user8@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user9','User Nine','Test user 9',2,1,(SELECT id FROM roles WHERE role_name='user'),'user9@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user10','User Ten','Test user 10',2,1,(SELECT id FROM roles WHERE role_name='user'),'user10@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user11','User Eleven','Test user 11',0,1,(SELECT id FROM roles WHERE role_name='user'),'user11@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user12','User Twelve','Test user 12',0,1,(SELECT id FROM roles WHERE role_name='user'),'user12@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user13','User Thirteen','Test user 13',0,1,(SELECT id FROM roles WHERE role_name='user'),'user13@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user14','User Fourteen','Test user 14',0,1,(SELECT id FROM roles WHERE role_name='user'),'user14@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user15','User Fifteen','Test user 15',0,1,(SELECT id FROM roles WHERE role_name='user'),'user15@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user16','User Sixteen','Test user 16',0,1,(SELECT id FROM roles WHERE role_name='user'),'user16@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user17','User Seventeen','Test user 17',0,1,(SELECT id FROM roles WHERE role_name='user'),'user17@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user18','User Eighteen','Test user 18',0,1,(SELECT id FROM roles WHERE role_name='user'),'user18@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user19','User Nineteen','Test user 19',0,1,(SELECT id FROM roles WHERE role_name='user'),'user19@mail.com'),
    ('$2a$12$xtDdBs00HBBq4Ayz7G9eueNc/0opjCpp44Il60.7d29W9w/hAhsdO','user20','User Twenty','Test user 20',0,1,(SELECT id FROM roles WHERE role_name='user'),'user20@mail.com');

INSERT INTO subscriptions (new_subscript, new_follower)
VALUES
    ('user1@mail.com','user2@mail.com'),
    ('user1@mail.com','user3@mail.com'),
    ('user2@mail.com','user1@mail.com'),
    ('user2@mail.com','user4@mail.com'),
    ('user3@mail.com','user1@mail.com'),
    ('user4@mail.com','user5@mail.com'),
    ('user5@mail.com','user1@mail.com'),
    ('user5@mail.com','user2@mail.com'),
    ('user6@mail.com','user7@mail.com'),
    ('user7@mail.com','user8@mail.com'),
    ('user8@mail.com','user9@mail.com'),
    ('user9@mail.com','user10@mail.com'),
    ('user10@mail.com','user6@mail.com'),
    ('user11@mail.com','user1@mail.com'),
    ('user12@mail.com','user2@mail.com'),
    ('user13@mail.com','user3@mail.com'),
    ('user14@mail.com','user4@mail.com'),
    ('user15@mail.com','user5@mail.com'),
    ('user16@mail.com','user6@mail.com'),
    ('user17@mail.com','user7@mail.com'),
    ('user18@mail.com','user8@mail.com'),
    ('user19@mail.com','user9@mail.com'),
    ('user20@mail.com','user10@mail.com');