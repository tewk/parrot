
# This file automatically generated by build/gen_settings.pl.

# From src/settings/Array.pm

=begin

=head2 Array Methods

These methods extend the native NQP Array class to support more of the basic
functionality expected for Perl 6 Hashes.

=end

module ResizablePMCArray {


=begin

=over 4

=item @reversed := @array.reverse

Return a C<@reversed> copy of the C<@array>.

=end

    method reverse () {
        my @reversed;
        for self { @reversed.unshift($_); }
        @reversed;
    }

=begin

=item $string := @array.join($join_string)

Join C<@array> using C<$join_string>

=end

    method join ($join_string) {
        return Q:PIR{
            $P0 = find_lex '$join_string'
            $S0 = $P0
            $S1 = join $S0, self
            %r  = box $S1
        }
    }

=begin

=back

=end

}

sub join($join_string, *@list) { @list.join($join_string) }

sub list(*@list) { @list };



# vim: ft=perl6
# From src/settings/Hash.pm

=begin

=head2 Hash Methods

These methods extend the native NQP Hash class to support more of the basic
functionality expected for Perl 6 Hashes.

=end

module Hash {


=begin

=over 4

=item $found := %hash.exists($key)

Return a true value if C<$key> exists in C<%hash>, or a false value otherwise.

=end

    method exists ($key) {
        return Q:PIR{
            $P1 = find_lex '$key'
            $I0 = exists self[$P1]
            %r  = box $I0
        };
    }

=begin

=item %hash.delete($key)

Delete C<$key> from C<%hash>.

=end

    method delete ($key) {
        Q:PIR{
            $P1 = find_lex '$key'
            delete self[$P1]
        };
    }


=begin

=item @keys := %hash.keys

Return all the C<@keys> in the C<%hash> as an unordered array.

=end

    method keys () {
        my @keys;
        for self { @keys.push($_.key); }
        @keys;
    }


=begin

=item @values := %hash.values

Return all the C<@values> in the C<%hash> as an unordered array.

=end

    method values () {
        my @values;
        for self { @values.push($_.value); }
        @values;
    }


=begin

=item @flattened := %hash.kv

Flatten C<%hash> into an array, alternating key and value.  This is useful
when iterating over key and value simultaneously:

    for %hash.kv -> $k, $v { ... }

=end

    method kv () {
        my @kv;
        for self { @kv.push($_.key); @kv.push($_.value); }
        @kv;
    }


=begin

=back

=end

}

=begin

=item %hash := hash(:key1(value1), :key2(value2), ...)

Coerce a list of pairs into a hash.

=end

sub hash (*%h) { return %h }

# vim: ft=perl6
# From src/settings/String.pm



=begin

These functions add more power to the basic regex matching capability,
including doing global matches and global substitutions.

=over 4

=item @matches := all_matches($regex, $text)

=end

sub all_matches($regex, $text) {
    my @matches;

    my  $match := $text ~~ $regex;
    while $match {
        @matches.push($match);
        $match := $match.CURSOR.parse($text, :rule($regex), :c($match.to));
    }

    return @matches;
}

=begin

=item $edited := subst($original, $regex, $replacement)

Substitute all matches of the C<$regex> in the C<$original> string with the
C<$replacement>, and return the edited string.  The C<$regex> must be a regex
object as returned by C</.../>.

The C<$replacement> may be either a simple string or a sub that will be called
with each match object in turn, and must return the proper replacement string
for that match.

=end

sub subst($original, $regex, $replacement) {
    my @matches := all_matches($regex, $original);
    my $edited  := pir::clone($original);
    my $is_sub  := pir::isa($replacement, 'Sub');
    my $offset  := 0;

    for @matches -> $match {
        my $replace_string := $is_sub ?? $replacement($match) !! $replacement;
	my $replace_len    := pir::length($replace_string);
	my $match_len      := $match.to - $match.from;
	my $real_from      := $match.from + $offset;

	Q:PIR{
             $P0 = find_lex '$edited'
	     $S0 = $P0
	     $P1 = find_lex '$real_from'
	     $I0 = $P1
	     $P2 = find_lex '$match_len'
	     $I1 = $P2
	     $P3 = find_lex '$replace_string'
	     $S1 = $P3
	     substr $S0, $I0, $I1, $S1
	     $P0 = $S0
	};

	$offset := $offset - $match_len + $replace_len;
    }

    return $edited;
}


# vim: ft=perl6
# From src/settings/IO.pm

=begin

=head1 NAME

IO.nqp - IO related functions for NQP.

=head1 SYNOPSIS

    # I/O
    print('things', ' to ', 'print', ...);
    say(  'things', ' to ', 'say',   ...);
    $contents := slurp($filename);
    spew(  $filename, $contents);
    append($filename, $contents);

=head1 I/O Functions

Basic stdio and file I/O functions.

=over 4

=item $contents := slurp($filename)

Read the C<$contents> of a file as a single string.

=end

sub slurp ($filename) {
    my $fh       := pir::open__Pss($filename, 'r');
    my $contents := $fh.readall;
    pir::close($fh);

    return $contents;
}


=begin

=item spew($filename, $contents)

Write the string C<$contents> to a file.

=end

sub spew ($filename, $contents) {
    my $fh := pir::open__Pss($filename, 'w');
    $fh.print($contents);
    pir::close($fh);
}


=begin

=item append($filename, $contents)

Append the string C<$contents> to a file.

=end

sub append ($filename, $contents) {
    my $fh := pir::open__Pss($filename, 'a');
    $fh.print($contents);
    pir::close($fh);
}


=begin

=back

=end

# vim: ft=perl6

# vim: set ft=perl6 nomodifiable :