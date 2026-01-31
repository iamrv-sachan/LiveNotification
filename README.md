# Zwigato: Real-time Order Tracking with Android Baklava

Zwigato is a concept food-delivery tracking application designed to showcase the power of **Android Baklava’s** new `ProgressStyle` notifications. It mimics the high-fidelity tracking experience seen in apps like Zomato, using smooth, segmented progress bars and custom tracker icons.

---

## 📽️ Project Demo

<img width="1512" height="982" alt="image" src="https://github.com/user-attachments/assets/2c1653bc-f4dd-43d7-90f8-75a824e85005" />


https://github.com/user-attachments/assets/a4318d94-cf8d-4f48-b8d1-ffa2abb96dd2



---

## 🚀 Features

* **Android Baklava `ProgressStyle**`: Implements the latest notification APIs for smooth, granular progress tracking.
* **Segmented Progress**: Visualizes the order journey through distinct stages: Confirmed, Preparing, En Route, and Delivered.
* **Custom Tracker Icons**: Dynamic notification icons that change based on the delivery status (e.g., showing a bike when the valet is en route).
* **Foreground Service**: Reliable background updates using `ForegroundService` with `specialUse` type to ensure the user stays informed even when the app is closed.
* **Smooth State Management**: Uses Kotlin Coroutines and StateFlow to simulate 60fps-like progress updates.

---

## 🛠️ Architecture & Tech Stack

The project is built with modern Android development standards:

* **Language**: Kotlin
* **UI**: Jetpack Compose
* **Asynchronous Flow**: Kotlin Coroutines & StateFlow
* **Component Architecture**: ViewModel & Clean Separation of Concerns
* **Android APIs**:
* `NotificationManager` for channel management.
* `Notification.Builder` with `ProgressStyle` (Baklava exclusive).
* `Parcelize` for efficient data passing between Activity and Service.



---

## 📂 Project Structure

* **`NotificationService`**: A Foreground Service that handles the lifecycle of the order notification.
* **`LiveNotificationManager`**: The core logic for styling the notification, handling Zomato-themed colors, and configuring the new Baklava segments.
* **`OrderViewModel`**: Simulates the order lifecycle, emitting real-time progress updates from 0 to 100%.
* **`OrderStatus`**: A sealed interface representing the five key stages of food delivery.

---

## ⚙️ Setup & Installation

1. **Prerequisites**:
* Android Studio (Latest Preview version supporting Android Baklava).
* An Emulator or physical device running **Android 16 (Baklava)**.


2. **Clone the Repository**:
```bash
git clone https://github.com/yourusername/zwigato.git

```


3. **Permissions**:
The app requires `POST_NOTIFICATIONS` and `FOREGROUND_SERVICE`. Ensure these are granted when prompted.
4. **Run**: Build and run the `app` module. The notification will trigger automatically as the simulated order starts.

---

## 🎨 Branding

The app utilizes Zomato's signature color palette:

* **ZOMATO_RED**: `#E23744` (Primary Branding)
* **ZOMATO_GRAY**: `#FAB9B9` (Inactive Segments)
* **ZOMATO_SUCCESS**: `#27AE60` (Completion State)

---

## 📝 License

This project is for educational purposes, demonstrating upcoming Android features.

---

Would you like me to generate a specific **GitHub Action** workflow file to help you automate builds for this project?
