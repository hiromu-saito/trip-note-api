INSERT
INTO users(
     user_id,
     mail_address,
     password
)
SELECT
    COALESCE(MAX(user_id) + 1, 1),
    /* user.mailAddress */'',
    /* user.password */''
from users;