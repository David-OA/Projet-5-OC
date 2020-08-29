package com.oconte.david.mynews;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

import static androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;

public class DecoratedFooServer /*implements DecoratedFooServer.FooServer*/ {

    //void registerIdleTransitionCallback (IdlingResource.ResourceCallback resourceCallback){}

        public interface FooServer {
            //public Foo newFoo();
            public TopStorieViewTest newFoo();
            public void updateFoo(TopStorieViewTest foo);
        }

        private final FooServer realFooServer;
        private final CountingIdlingResource fooServerIdlingResource;

        public DecoratedFooServer(FooServer realFooServer, CountingIdlingResource fooServerIdlingResource) {
            this.realFooServer = checkNotNull(realFooServer);
            this.fooServerIdlingResource = checkNotNull(fooServerIdlingResource);
        }

        public TopStorieViewTest newFoo () {
            fooServerIdlingResource.increment();
            try {
                return realFooServer.newFoo();
            } finally {
                fooServerIdlingResource.decrement();
            }
        }

        public void updateFoo (TopStorieViewTest foo){
            fooServerIdlingResource.increment();
            try {
                realFooServer.updateFoo(foo);
            } finally {
                fooServerIdlingResource.decrement();
            }
        }

}
