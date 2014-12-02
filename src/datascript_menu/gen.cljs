(ns datascript-menu.gen)

(def girls ["Amelia" "Olivia" "Jessica" "Emily" "Lily" "Ava" "Isla" "Sophie" "Mia" "Isabella" "Evie" "Poppy" "Ruby" "Grace" "Sophia" "Chloe" "Freya" "Isabelle" "Ella" "Charlotte" "Scarlett" "Daisy" "Lola" "Holly" "Eva" "Lucy" "Millie" "Phoebe" "Layla" "Maisie" "Sienna" "Alice" "Florence" "Lilly" "Ellie" "Erin" "Elizabeth" "Imogen" "Summer" "Molly" "Hannah" "Sofia" "Abigail" "Jasmine" "Matilda" "Megan" "Rosie" "Lexi" "Lacey" "Emma" "Amelie" "Maya" "Gracie" "Emilia" "Georgia" "Hollie" "Evelyn" "Eliza" "Amber" "Eleanor" "Bella" "Amy" "Brooke" "Leah" "Esme" "Harriet" "Anna" "Katie" "Zara" "Willow" "Elsie" "Annabelle" "Bethany" "Faith" "Madison" "Isabel" "Rose" "Julia" "Martha" "Maryam" "Paige" "Heidi" "Maddison" "Niamh" "Skye" "Aisha" "Mollie" "Ivy" "Francesca" "Darcey" "Maria" "Zoe" "Keira" "Sarah" "Tilly" "Isobel" "Violet" "Lydia" "Sara" "Caitlin"])

(def boys ["Harry" "Oliver" "Jack" "Charlie" "Jacob" "Thomas" "Alfie" "Riley" "William" "James" "Joshua" "George" "Ethan" "Noah" "Samuel" "Daniel" "Oscar" "Muhammad" "Max" "Archie" "Leo" "Joseph" "Tyler" "Henry" "Mohammed" "Alexander" "Lucas" "Dylan" "Isaac" "Logan" "Benjamin" "Mason" "Jake" "Harrison" "Finley" "Edward" "Freddie" "Adam" "Jayden" "Zachary" "Sebastian" "Lewis" "Ryan" "Theo" "Luke" "Matthew" "Harley" "Harvey" "Toby" "Liam" "Arthur" "Michael" "Callum" "Tommy" "Jenson" "Nathan" "Bobby" "Mohammad" "David" "Connor" "Luca" "Charles" "Jamie" "Frankie" "Kai" "Alex" "Blake" "Reuben" "Aaron" "Dexter" "Jude" "Stanley" "Leon" "Elliot" "Gabriel" "Ollie" "Louie" "Aiden" "Cameron" "Louis" "Owen" "Finlay" "Elijah" "Frederick" "Hugo" "Caleb" "Taylor" "Sonny" "Seth" "Kyle" "Elliott" "Robert" "Kian" "Theodore" "Kayden" "Rhys" "Rory" "Bailey" "Evan" "Hayden"])

(def family ["Smith" "Jones" "Williams" "Brown" "Taylor" "Davies" "Wilson" "Evans" "Thomas" "Johnson" "Roberts" "Walker" "Wright" "Robinson" "Thompson" "White" "Hughes" "Edwards" "Green" "Hall" "Wood" "Harris" "Lewis" "Martin" "Jackson" "Clarke" "Clark" "Turner" "Hill" "Scott" "Cooper" "Morris" "Ward" "Moore" "King" "Watson" "Baker" "Harrison" "Morgan" "Patel" "Young" "Allen" "Mitchell" "James" "Anderson" "Phillips" "Lee" "Bell" "Parker" "Davis"])

(defn gen-name []
  (str
    (rand-nth (if (< (rand) 0.487) girls boys))
    " "
    (rand-nth family)))

(def positions {
  "Gnocchi Sorento Style (v)" "with tomato sauce, melted cheddar, grated parmesan cheese and a fresh basil leaf"
  "Chicken And Mushroom Pie" "served with mashed potatoes and heritage carrots"
  "Higgledy Veggie Pie (v)" "in a rich white sauce served with grilled mushrooms and spinach"
  "Chicken Breast Milanese Style" "served with roast tomato, rocket salad and chips"
  "Grilled Salmon Fillet" "served with new potatoes, baby spinach and gooseberry sauce"
  "Beef Stew" "chunks of beef, carrot, potatoes in gravy with mashed potatoes on top"
  "Roasted Pork Belly" "roasted Berkshire pork belly, baby onions and black pudding"
  "Pan Fried Sea Bass Fillet" "served with new potatoes, rocket salad, bacon and pesto genovese sauce"
  "Classic Beef Burger" "8oz Aberdeen Angus beef burger, Red Leicester cheese, tomato relish and hand cut chips"
  "Fish And Chips" "beer battered Haddock, hand cut chips, mushy peas and tartar sauce"
})
