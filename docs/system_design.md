# Aether AI - User Data Migration Design

## Goal

Allow users to seamlessly migrate their chat history, profile, reminders, personality traits, and settings when moving to a new device while keeping all personal data secure.

## What Gets Backed Up

The following on-device data is included in backups:

* UserProfile (name, age, phone number, personality traits)
* ChatMessage history
* Reminder data
* App preferences stored in DataStore
* User settings and onboarding status

## Storage Location

A backup file is generated locally on the device and uploaded to the user's cloud storage account.

Example:

* Google Drive (Android)
* Encrypted local export file

## File Format & Encryption

Backup Format:

```json
{
  "userProfile": {},
  "chatMessages": [],
  "reminders": [],
  "settings": {}
}
```

Encryption:

* AES-256 encryption
* User-specific encryption key
* Encrypted before upload
* Decrypted only during restore

## Restore Flow

1. User installs Aether AI on a new device.
2. User selects "Restore Backup".
3. User signs into cloud account.
4. Backup file is downloaded.
5. Backup is decrypted.
6. UserProfile is restored into DataStore.
7. ChatMessages are restored into Room.
8. Reminders are restored into Room.
9. Settings and onboarding state are restored.
10. User resumes from the exact previous state.

## What Is Not Backed Up

The following data is intentionally excluded:

* Temporary cache files
* Audio recordings
* Logs and analytics data
* Sync worker runtime state

Reason:

These files can be regenerated and do not affect user experience.
