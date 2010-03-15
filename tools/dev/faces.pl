#! perl
# $Id$
# Copyright (C) 2010, Parrot Foundation.

use strict;
use warnings;

use Gravatar::URL;

=for usage

use to generate source for http://trac.parrot.org/parrot/wiki/ParrotFaces

=cut

open my $fh, '<', 'CREDITS';

my %urls;
while(<$fh>) {
    next unless /^E: (.*)/;
    my $email = lc $1;
    $urls{$email} = gravatar_url(
        email   => $email,
        rating  => 'r',
        size    => 80,
        default => 'wavatar',
    );
}

foreach my $email (sort keys %urls) {
    print "[[Image($urls{$email},title=$email)]]\n";
}
print "''Generated by tools/dev/faces.pl''\n";

# Local Variables:
#   mode: cperl
#   cperl-indent-level: 4
#   fill-column: 100
# End:
# vim: expandtab shiftwidth=4:
