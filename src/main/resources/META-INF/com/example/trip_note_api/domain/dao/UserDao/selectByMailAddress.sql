SELECT
    /*%expand*/*
FROM
    users
WHERE
        mail_address = /* mailAddress */''
    AND delete_flag = 0;
