# Elden Ring Seamless Coop Mod Manager

The Elden Ring Seamless Coop Mod Manager is a Java application designed to simplify the process of managing and installing new versions of the Elden Ring Seamless Coop Mod. 
This tool automates version checking, downloading, and installation, ensuring you always have the latest version of the mod with minimal effort.

Features:

- One-Click Updates: Easily update to the latest version with a single click.
- Backup Management: Automatically creates backups of your current mod version and settings before updating.
- Mod Installation: Simplifies the process of installing the mod for first-time users.
- Version History: Keeps track of installed versions and allows for easy rollback if needed.

## System Requirements

- Windows 10 or later
- Java Runtime Environment (JRE) 11 or later
- Elden Ring game installed
- [The Elden Ring Seamless Coop mod](https://www.nexusmods.com/eldenring/mods/510?tab=files) zip file downloaded to your Downloads directory

**This software has only been tested for Windows since it is tightly coupled with the Elden Ring mod**

## Installation

Download the latest release of the Elden Ring Seamless Coop Mod Manager from the [Releases page](https://github.com/cbartram/elden-ring-auto-update/releases). 
Extract the downloaded ZIP file to a location of your choice. Run the EldenRingModManager.exe file to start the application.

## Usage

On first run, the application will ask you to locate your Elden Ring installation directory. Generally this will be installed 
in `C:\SteamLibrary\steamapps\common\ELDEN RING`. Do **NOT** include the `Game` directory.

Simply click the "Select Elden Ring Install Location" to bring up a file picker and choose your `ELDEN RING` directory.

![./images/ersc_mod_manager.png](mod-manager-screenshot)

The mod manager will automatically look in your `Downloads` directory for any zip files for the Elden Ring Seamless Coop mod.
Generally the correct and latest mod version should be selected however, if it is not you can use the "Choose Zip" button to select the version you would like to 
unpack and install.

Finally, you can select the "Make shortcut" option to create a shortcut for the Elden Ring Seamless Coop launcher exe file if you wish.

When you click "Install" the mod manager will install the selected version to the Elden Ring install location creating a backup of your settings file in case of 
corruption.

## Launching the Game

Use the "ersc_launcher.exe" file to start the game with the mod automatically applied. If you would like to create a shortcut
to this file to keep elsewhere make sure to check the "Create Shortcut" option from the UI.

## Contributing
We welcome contributions to the Elden Ring Seamless Coop Mod Manager! Please see our CONTRIBUTING.md file for details on how to get started.

## License
This project is licensed under the MIT License - see the LICENSE.md file for details.

## Acknowledgments

Thanks to the creators of the Elden Ring Seamless Coop Mod for their amazing work.
This project is not affiliated with or endorsed by FromSoftware, Inc. or Bandai Namco Entertainment.

## Contact

If you have any questions, issues, or suggestions, please open an issue on our GitHub repository and I will take a look
as soon as I am able!

## Built With

- Java 11 - Programming Language
- Java FX - UI Library for Java
- Lombok - Annotations for simplified Java development
- Gradle - Build Tool