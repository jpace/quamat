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

```text
values[π]: 3.1415
values[φ]: 1.618
values[√2]: 1.414
```
