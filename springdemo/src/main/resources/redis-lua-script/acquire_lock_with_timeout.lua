if redis.call('setNx',KEYS[1],ARGV[1]) then
    redis.log(redis.LOG_WARNING, "Something is wrong with this script111111111.")

    if redis.call('get',KEYS[1])==ARGV[1] then
        redis.log(redis.LOG_WARNING, "Something is wrong with this script22222222.")
        return redis.call('expire',KEYS[1],ARGV[2])
    else
        return 0
    end
end
