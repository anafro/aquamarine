[![The Lush Page Banner](https://github.com/anafro/anafro/blob/main/Banners/Lush.Banner.png?raw=true "Press the right mouse button and then press 'Copy the URL' to copy the URL to Lush page")](https://github.com/anafro/lush-interpreter)

<h1 align="center">Lush</h1>
<hr>

Lush is a scripting language written in Java. The general idea for the language is to be as user-friendly as possible. 
Everything in Lush you see - error messages, function names, and syntax - it's all crafted
to simplify the programmers' lives.

## What's so special about Lush?

### Variable naming

For example, let's see how to create a variables in Lush:

```sass
My name: 'John'
```

As we can see, Lush has a special naming case (which probably doesn't have a name yet lol).
Let's call it ✨The Lush case✨. It's just how you spell words in the sentence.

### Operators
We also changed some operators. We think that dot looks a bit confusing for new programmers, so we changed it to an arrow `->`.

For example, to print a message to console, use this code:

```sass
Terminal -> Write: 'Hello world'
```

And yes, function call and variable definition use the same operator called "Pass" `:`.

We changed the boolean operators too! Instead of double 'or' and 'and', use a single character like so:

```sass
If: Age > 18 & Money > 100 | Has VIP status { ...
```

Isn't it beautiful? Oh, and as you can notice, we don't use `=` as a assignment operator. Instead, it's an 'equals' operator!

```sass
If: User password = Password within database { ...
```

## Wait. No keywords?
Yes! In Lush, we don't need them. Instead, we made everything a function, which simplifies everything a lot!
And yes again! Even `If`, `For`, `Import`, and other constructions are functions too.

## Making my own functions
To create a function with no parameters, just write the name and the code in curly brackets:
```sass
Print hello world 3 times {
    Terminal -> Write: 'Hello world'
    Terminal -> Write: 'Hello world'
    Terminal -> Write: 'Hello world'
}

Print hello world 3 times!
```

To make a function with arguments, use this syntax:
```sass
Print multiple times: (Message: Text, Times: Int) {
    Repeat: Times {
        Terminal -> Write: Message
    }
}

Print multiple times: 'Hello world', 3
```

## Object-oriented programming
Lush is object-oriented programming as well as your favorite language (we hope that it's not C he-he).
Any yes, to create a class, you need some functions too! See an example:

```sass
Class: User {
    Property: Name
    Hidden: Age
  
    Create: (Name, Age) {
        User -> Name: Name
        User -> Age: Age
    }
  
    Introduce {
        Terminal -> Write: 'Hi, my name is #{Name}, I am #{Age} years old'  
    }
}

John: Make user: "John", 28
John -> Introduce!
```

## Arrays? We've got something more interesting!
In other languages, there are several types of collections: lists, arrays, sets, stacks, queues and so on.
We've tried to unify all the collections into one type: `Set`. Don't be misguided: it is not a common set. It's a customizable
collection of items. By default, it behaves like a list.

```sass
My friends: Make set!
My friends -> Add: 'Me'
My friends -> Add: 'Myself'
My friends -> Add: 'I'

Terminal -> Variable: My friends       === My friends = ["Me", "Myself", "I"]
```

You can replace the `Make set!` with just square brackets though!

```sass
Shopping list: []

Shopping list -> Add: 'Apple'
Shopping list -> Add: 'Banana'
Shopping list -> Add: 'Potatoes'
Shopping list -> Add: 'Butter'
```

If you need a unique list of something, just after creation the list, use `Ignore duplicated`

```sass
Favorite numbers: []
Favorite numbers -> Ignore duplicated!

Favorite numbers -> Add: 1
Favorite numbers -> Add: 1
Favorite numbers -> Add: 2
Favorite numbers -> Add: 3
Favorite numbers -> Add: 3
Favorite numbers -> Add: 4

Terminal -> Write: Favotire numbers   === [1, 2, 3, 4]
```