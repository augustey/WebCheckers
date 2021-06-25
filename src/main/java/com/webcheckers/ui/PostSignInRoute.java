package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.webcheckers.ui.GetHomeRoute.PLAYER_KEY;

/**
 * The UI Controller to POST the Sign-in page.
 *
 * @author <a href="mailto:jrl9984@rit.edu">Jim Logan</a>
 */
public class PostSignInRoute implements Route
{
    // Attributes
    public static final String USERNAME_PARAM = "username";
    public static final String NOT_VALID_USERNAME = "notValid";
    public static final String SIGNED_IN = "signedIn";

    // Text
    public static final String NON_UNIQUE_USERNAME = "The name you entered is already in the system";
    public static final String NON_ALPHANUMERIC_USERNAME = "The name you entered does not contain a letter or a number";

    // State
    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /signin} HTTP requests.
     *
     * @param playerLobby
     *     The server wide lobby keeping track of all players.
     *
     * @param templateEngine
     *     The HTML template rendering engine
     */
    public PostSignInRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine)
    {
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }

    /**
     * Render the WebCheckers Sign-in page.
     *
     * @param request
     *     The HTTP request.
     *
     * @param response
     *     The HTTP response.
     *
     * @return
     *     The rendered HTML for the Sign-in page.
     */
    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();

        final Map<String, Object> vm = new HashMap<>();

        final String nameString = request.queryParams(USERNAME_PARAM);
        Player player = new Player(nameString);

        vm.put(GetSignInRoute.TITLE_ATTR, GetSignInRoute.TITLE);

        switch (playerLobby.signIn(player))
        {
            case NON_UNIQUE:
                vm.put(SIGNED_IN, false);
                vm.put(NOT_VALID_USERNAME, true);
                vm.put("invalidMessage", NON_UNIQUE_USERNAME);
                break;
            case NON_ALPHANUMERIC:
                vm.put(SIGNED_IN, false);
                vm.put(NOT_VALID_USERNAME, true);
                vm.put("invalidMessage", NON_ALPHANUMERIC_USERNAME);
                break;
            case SUCCESS:
                httpSession.attribute(PLAYER_KEY, player);
                vm.put(PLAYER_KEY, player);
                vm.put(SIGNED_IN, true);
                vm.put("notValid", false);
                response.redirect("./");
                break;
        }

        return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
    }
}
