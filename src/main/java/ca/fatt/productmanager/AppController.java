package ca.fatt.productmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
    @Autowired //Inject an instance of the ProductService
    private ProductService service;

    @RequestMapping("/") //home page
    public String viewHomePage(Model model) { //Set the list of products to the model (Product), therefore declare here
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "index"; //HTML file name
    }

    @RequestMapping("/new")
    public String showNewProductForm(Model model) { //What to show
        Product product = new Product();
        model.addAttribute("product", product); //product must match the object in the HTML form
        return "new_product";//HTML file name

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST) //If using a method for a form, need to specify the method for the form
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);

        return "redirect:/"; //This will redirect back to the index home page
    }

    @RequestMapping("/edit/{id}") //Use ModelAndView to return the model and view in one value
    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) { //Use @PathVariable to grab the ID of the product from the table
        ModelAndView mav = new ModelAndView("edit_product"); //URL of the edit form
        Product product = service.get(id);
        mav.addObject("product", product);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        service.delete((id));
        return "redirect:/"; //Send to index
    }
}
