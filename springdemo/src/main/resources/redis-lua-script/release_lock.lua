redis.log(redis.LOG_WARNING, "Something is wrong with this scriptaaaaaaaa.")
redis.log(redis.LOG_WARNING, ARGV[1])
redis.log(redis.LOG_WARNING, redis.call('get',KEYS[1]))
if redis.call('get',KEYS[1]) == ARGV[1] then
    redis.log(redis.LOG_WARNING, "Something is wrong with this script333333333.")
    return redis.call('del',KEYS[1])
else
    return 0
end