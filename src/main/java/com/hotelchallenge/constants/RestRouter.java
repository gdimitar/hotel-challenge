package com.hotelchallenge.constants;

public final class RestRouter {

    public class User {

        public static final String REGISTER = "/api/user/register";

        private User() {
        }
    }

    public class Hotel {

        public static final String REGISTER = "/api/hotel/register";
        public static final String LIST = "/api/hotels";
        public static final String VIEW = "/api/hotels/{id}";
        public static final String SEARCH = "/api/hotels/search";

        private Hotel() {
        }
    }

    public class Favorites {

        public static final String ADD = "/api/hotels/favorites";
        public static final String REMOVE = "/api/hotels/favorites/{id}";
        public static final String VIEW = "/api/hotels/favorites/{userId}";
    }

    public class Reviews {

        public static final String ADD = "/api/reviews";
        public static final String LIST = "/api/reviews/{hotelId}";

        private Reviews() {

        }
    }

    public class Like {

        public static final String ADD = "/api/likes/add";
        public static final String REMOVE = "/api/likes/remove";

        private Like() {

        }
    }
}
