SELECT /*%expand*/* FROM users
  WHERE
      mail_address = /* user.mailAddress */''  AND
      password = /* user.password */''         AND
      delete_flag = /* user.deleteFlag */''
