package by.htp.ishop.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.ishop.controller.command.Command;
import by.htp.ishop.controller.command.CommandName;
import by.htp.ishop.controller.command.impl.AddProductFavorites;
import by.htp.ishop.controller.command.impl.AddToCart;
import by.htp.ishop.controller.command.impl.ChangeLocal;
import by.htp.ishop.controller.command.impl.Checkout;
import by.htp.ishop.controller.command.impl.DeleteProductCart;
import by.htp.ishop.controller.command.impl.DeleteProductFavorites;
import by.htp.ishop.controller.command.impl.Favorites;
import by.htp.ishop.controller.command.impl.GoToCart;
import by.htp.ishop.controller.command.impl.GoToCheckout;
import by.htp.ishop.controller.command.impl.GoToErrorPage;
import by.htp.ishop.controller.command.impl.GoToMain;
import by.htp.ishop.controller.command.impl.GoToRegistrationPage;
import by.htp.ishop.controller.command.impl.GoToSignInPage;
import by.htp.ishop.controller.command.impl.GroupProductBySubcategory;
import by.htp.ishop.controller.command.impl.Order;
import by.htp.ishop.controller.command.impl.ProductInfo;
import by.htp.ishop.controller.command.impl.Registration;
import by.htp.ishop.controller.command.impl.SignIn;
import by.htp.ishop.controller.command.impl.SignOut;
import by.htp.ishop.controller.command.impl.Subcategory;
import by.htp.ishop.controller.command.impl.WrongRequest;

final class CommandProvider {

	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {

		repository.put(CommandName.SIGNIN, new SignIn());
		repository.put(CommandName.GO_TO_SIGNIN, new GoToSignInPage());		
		repository.put(CommandName.GO_TO_REGISTRATION, new GoToRegistrationPage());		
		repository.put(CommandName.REGISTRATION, new Registration());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
		repository.put(CommandName.CHANGELOCAL, new ChangeLocal());
		repository.put(CommandName.GO_TO_MAIN, new GoToMain());

		repository.put(CommandName.SIGNOUT, new SignOut());
		repository.put(CommandName.SUBCATEGORY, new Subcategory());
		repository.put(CommandName.GROUP_PRODUCT_BY_SUBCATEGORY, new GroupProductBySubcategory());
		repository.put(CommandName.GO_TO_PRODUCT_INFO, new ProductInfo());
		repository.put(CommandName.ADD_TO_CART, new AddToCart());
		repository.put(CommandName.GO_TO_CART, new GoToCart());
        repository.put(CommandName.DELETE_PRODUCT_IN_CART, new DeleteProductCart());
        repository.put(CommandName.GO_TO_CHECKOUT, new GoToCheckout());
        repository.put(CommandName.ORDER, new Order());
        repository.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
        
        repository.put(CommandName.ADD_PRODUCT_FAVORITES, new AddProductFavorites());
        repository.put(CommandName.DELETE_PRODUCT_FAVORITES, new DeleteProductFavorites());
        repository.put(CommandName.FAVORITES, new Favorites());
        repository.put(CommandName.CHECKOUT, new Checkout());


	}

	Command getCommand(String name) {

		CommandName commandName = null;
		Command command = null;

		try {

			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);

		} catch (IllegalArgumentException | NullPointerException e) {

			command = repository.get(CommandName.WRONG_REQUEST);
		}
		return command;
	}
}
