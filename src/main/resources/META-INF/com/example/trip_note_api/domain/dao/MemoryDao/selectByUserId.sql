SELECT /*%expand*/* FROM memories
WHERE
    user_id = /* userId */'' AND
    delete_flag = 0;
