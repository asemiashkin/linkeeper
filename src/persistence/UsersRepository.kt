package com.asemiashkin.persistence

import com.asemiashkin.User

interface UsersRepository : CRUDRepository<Long, User>