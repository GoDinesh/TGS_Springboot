package com.tgsbhadohi.TGS.controller.notification;


import com.tgsbhadohi.TGS.entities.notifications.FeesNotification;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/api/notifications")
public class FeesNotificationController {

    // Reactive sink to emit notifications
    private final Sinks.Many<FeesNotification> notificationSink = Sinks.many().multicast().onBackpressureBuffer();

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<FeesNotification> streamNotifications() {
        return notificationSink.asFlux();
    }

    @PostMapping("/push")
    public String pushNotification(@RequestBody FeesNotification notification) {
        notificationSink.tryEmitNext(notification);
        return "Notification sent!";
    }
}

