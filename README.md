# quamat

Generates expressive output for objects, formatting
containers (arrays, lists, maps), complex Objects,
and primitives.

## Examples

### Common setup:

```java
List<String> lines = new ArrayList<>();
StringGenerator sg = new StringGenerator(new MessageFormatter(), new StringListWriter(lines));
Generator gen = new Generator(sg);
```

### Primitive

```java
gen.generate("x", 17);
writeLines(lines);
```

#### Output

```text
x: 17
```

### List

```java
result.clear();
List<String> list = new ArrayList<>();
list.add("alex");
list.add("david");
list.add("eddie");
list.add("michael");

gen.generate("members", list);
writeLines(lines);
```

#### Output

```text
members[0]: alex
members[1]: david
members[2]: eddie
members[3]: michael
```

### Map

```java
lines.clear();
Map<String, Object> map = new HashMap<>();
map.put("π", 3.1415);
map.put("φ", 1.618);
map.put("√2", 1.414);

gen.generate("values", map);
writeLines(lines);
```

#### Output

```text
values[π]: 3.1415
values[φ]: 1.618
values[√2]: 1.414
```

### Nested Containers

```java
List<String> lines = new ArrayList<>();
Generator gen = createGenerator(lines);

// from https://en.wikipedia.org/wiki/Portable_computer
List<Map<String, Object>> computers = new ArrayList<>();
Map<String, Object> values = new HashMap<>();
values.put("name", "SCAMP");
values.put("manufacturer", "IBM");
values.put("CPU", "PALM");
computers.add(values);

values = new HashMap<>();
values.put("name", "IBM 5100");
values.put("manufacturer", "IBM");
values.put("CPU", "PALM");
computers.add(values);

values = new HashMap<>();
values.put("name", "MIT Suitcase");
values.put("manufacturer", "MIT");
values.put("CPU", "Motorola 6800");
computers.add(values);

values = new HashMap<>();
values.put("name", "Xerox NoteTaker");
values.put("manufacturer", "Xerox");
Map<String, Object> cpu = new HashMap<>();
cpu.put("manufacturer", "Intel");
cpu.put("model", 8086);
values.put("CPU", cpu);
computers.add(values);

gen.generate("computers", computers);
writeLines(lines);
```

#### Output

```text
computers[0][name]: SCAMP
computers[0][CPU]: PALM
computers[0][manufacturer]: IBM
computers[1][name]: IBM 5100
computers[1][CPU]: PALM
computers[1][manufacturer]: IBM
computers[2][name]: MIT Suitcase
computers[2][CPU]: Motorola 6800
computers[2][manufacturer]: MIT
computers[3][name]: Xerox NoteTaker
computers[3][CPU][model]: 8086
computers[3][CPU][manufacturer]: Intel
computers[3][manufacturer]: Xerox
```
