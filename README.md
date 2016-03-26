# CrackshotSoundDesigner

Minecraft plugin to assist servers using Crackshot with a faster method of designing Crackshot sounds for weapons

### Installation
 - drop the jar into your server plugins foler
 - restart your server

### Commands
 - Nothing to configure

### Commands [] - denotes optional -- <> - denotes mandatory
 - /shotsound [all] <sound1>,<sound2>,<sound3>... 
    - all is optional
    - at least one sound is mandatory
 - /shotsounds
    - will list all sounds

### Customizable Sounds - there may be others this is what i quickly found looking through the Crackshot Guide
Can be used to assist in sound creation for the following sound effects:
  - Sounds_Aquired
  - Sounds_Shoot
  - Sounds_Out_Of_Ammo
  - Sounds_Reloading
  - Sounds_Shoot_With_No_Ammo
  - Sounds_Single_Reload

### Sound Format
All sounds follow the format: `SOUND-VOLUME-PITCH-DELAY.` should replace `<sound#>`
 - `SOUND` is the type of sound.
 - `VOLUME` is a value from 0-1 that determines how loud the sound is. Any value greater than 1 increases - the range of the sound.
 - `PITCH` is a value from 0-2 that determines the pitch of the sound.
 - `DELAY` is the delay in ticks before the sound is played.
 - By default, sounds have a `PITCH` and VOLUME of 1.
 - View the complete list of sounds to determine which sound is right for you by using the command /shotsounds

### Version
- 1.0

### Todos
 - No planned updates

### Change Log
 - No Changes as this time

Want to contribute? Fork It!