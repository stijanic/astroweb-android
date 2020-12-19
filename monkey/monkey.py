# export PATH=/home/stijanic/Software/android-studio/jre/bin:$PATH
# /home/stijanic/Software/Android/Sdk/emulator/emulator -list-avds
# /home/stijanic/Software/Android/Sdk/emulator/emulator -avd Nexus_4_API_29
# /home/stijanic/Software/Android/Sdk/tools/bin/uiautomatorviewer
# /home/stijanic/Software/Android/Sdk/tools/bin/monkeyrunner /home/stijanic/Projects/Astroweb-Android/monkey/monkey.py
import datetime
# Imports the monkeyrunner modules used by this program
from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice

# Connects to the current device, returning a MonkeyDevice object
device = MonkeyRunner.waitForConnection()

# Installs the Android package. Notice that this method returns a boolean, so you can test
# to see if the installation worked.
# device.removePackage('com.astroweb')
# MonkeyRunner.sleep (30)
device.installPackage('./app/build/outputs/apk/debug/app-debug.apk')

# sets a variable with the package's internal name
package = 'com.astroweb'

# sets a variable with the name of an Activity in the package
activity = 'com.astroweb.MainActivity'

# sets the name of the component to start
runComponent = package + '/' + activity

# Runs the component
device.startActivity(component=runComponent)

MonkeyRunner.sleep (10)

# Presses the Menu button
# device.press('KEYCODE_MENU', MonkeyDevice.DOWN_AND_UP)
i = 1
while i < 6:
    MonkeyRunner.sleep (5)
    device.touch(680, 1050, 'DOWN_AND_UP')
    i += 1

MonkeyRunner.sleep (5)

# Takes a screenshot
# Do not enable WMVare "Accelerate 3D graphics" for screenshots to work!!!
result = device.takeSnapshot()

shot = 'astroweb-shot-' + datetime.datetime.now().strftime("%Y%m%d-%H%M%S") + '.png'

# Writes the screenshot to a file
result.writeToFile(shot, 'png')
