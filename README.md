# JavaSound

## Maven dependency:

    <repositories>
        <repository>
            <id>mvn-repo</id>
            <url>https://raw.githubusercontent.com/cbudtz/JavaSound/master</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>org.budtz</groupId>
            <artifactId>JavaSound</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>
## Usage:
    import org.budtz.Player;
    
    Player player = new Player();
        player.playSoundAsync("You Shall Not Pass.wav");
        player.playSoundBlocking("You Shall Not Pass.wav");
