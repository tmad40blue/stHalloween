# stHalloween
[![Build Status](https://ts-mc.net/jenkins/buildStatus/icon?job=sthalloween)](https://ts-mc.net/jenkins/job/sthalloween)

stHalloween is meant to provide a spooky atmosphere on your server in the spirit of Halloween. It includes various features, as well as trick-or-treating that you can use as an event for the holiday!
<br>
<br>
<b>Requirements</b>
<ul>
<li>Java 8</li>
</ul>
<br>
<b>Functions</b>
<ul>
<li>Trick-or-treating, fully configurable</li>
<li>Play scary sounds to all players at a configurable interval</li>
<li>Spawn zombies/skeletons with pumpkins for helmets, configurable</li>
<li>Spawn temporary bats when hitting zombies/skeletons, configurable</li>
</ul>
<br>
<b>Features</b>
<ul>
<li>Change the content/color of all plugin messages</li>
<li>Trick-or-treating can be changed to your liking, like where the doors are, what rewards your players can get, and how often they'll get tricked</li>
<li>Anything/everything is able to be disabled</li>
</ul>

<br>
<b>Commands</b>
<ul>
<li>/sth reload</li>
</ul>
<br>
<b>Configuration</b>
<ul>
<li>Sounds</li>
<ul>
<li>Enabled: Whether cave sounds should periodically play to everyone online</li>
<li>Frequency: How often sounds should go off, in minutes</li>
</ul>
<li>Bats</li>
<ul>
<li>Enabled: Whether 3 bats should be spawned when zombies/skeletons are hit by players</li>
<li>Invulnerable Time: How long bats should be invulnerable after spawning, in seconds</li>
<li>Duration: How long bats should live after spawning, in seconds</li>
</ul>
<li>Trick-or-Treat</li>
<ul>
<li>Enabled: Whether players can trick or treat</li>
<li>Invulnerable Time: How long villagers/witches should be invulnerable after spawning, in seconds</li>
<li>Duration: How long villagers/witches should live after spawning, in seconds</li>
<li>Witch change: Chance of a player being tricked, 10 means a 10/100 or 10% chance</li>
</ul>
</ul>
<br>
<b>Treats/Tricks Config</b>
<ul>
<li>Chance: How many times a trick/treat should be entered into the list of possible tricks/treats, a higher number means a higher chance. Avoid larger numbers as this could lag your server</li>
<li>Command: Command to run if this trick/treat is chosen</li>
<li>Name: Display name for the treat</li>
</ul>
<br>
A note about trick-or-treating, players can trick-or-treat at every door once per day, this isn't configurable as it'd be too much of a hassle to really change, and I feel that for most people, it works fine like that.
<br>
<br>
<b>Doors Config</b><br>
Example config for the doors section
<a href="http://pastebin.com/3AT8czc0">Link</a>
<br>
<br>
<b>Permissions</b>
<ul>
<li>stHalloween.* (All permissions below)</li>
<ul>
<li>stHalloween.reload (Use /sth reload)</li>
</ul>
</ul>
