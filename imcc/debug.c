/*
 * print debug info of various structures
 *
 * handle info/error/warnins of imcc
 */


#include "imc.h"

void fatal(int code, const char *func, const char *fmt, ...)
{
    va_list ap;

    va_start(ap, fmt);
    fprintf(stderr, "error:imcc:%s: ", func);
    vfprintf(stderr, fmt, ap);
    va_end(ap);
    exit(code);
}


void fataly(int code, const char *func, int lin, const char *fmt, ...)
{
    va_list ap;

    va_start(ap, fmt);
    fprintf(stderr, "error:imcc:%s file %s line %d: ", func, sourcefile, lin);
    vfprintf(stderr, fmt, ap);
    va_end(ap);
    exit(code);
}


void warning(const char *func, const char *fmt, ...)
{
    va_list ap;

    va_start(ap, fmt);
    fprintf(stderr, "warning:imcc:%s: ", func);
    vfprintf(stderr, fmt, ap);
    va_end(ap);
}

void info(int level, const char *fmt, ...)
{
    va_list ap;

    if(level > IMCC_VERBOSE)
	return;

    va_start(ap, fmt);
    vfprintf(stderr, fmt, ap);
    va_end(ap);
}

void debug(int level, const char *fmt, ...)
{
    va_list ap;

    if ( !(level & IMCC_DEBUG))
	return;

    va_start(ap, fmt);
    vfprintf(stderr, fmt, ap);
    va_end(ap);
}

void dump_instructions() {
    Instruction *ins;
    Basic_block *bb;
    int pc;

    fprintf(stderr, "\nDumping the instructions status:\n-------------------------------\n");
    fprintf(stderr, "n\tblock\tdepth\tflags\ttype\topnum\tsize\tpc\top\n");
    for (pc = 0, ins = instructions; ins; ins = ins->next) {
	bb = bb_list[ins->bbindex];

	if (bb) {
	     fprintf(stderr, "%i\t%d\t%d\t%x\t%x\t%d\t%d\t%d\t",
		     ins->index, bb->index, bb->loop_depth,
                     ins->flags, ins->type, ins->opnum, ins->opsize, pc);
	}
	else {
	     fprintf(stderr, "\t");
	}

	fprintf(stderr, ins_string(ins));
	fprintf(stderr, "\n");
        pc += ins->opsize;
    }
    fprintf(stderr, "\n");
}

void dump_cfg() {
    int i;
    Basic_block *bb;
    Edge *e;

    fprintf(stderr, "\nDumping the CFG:\n-------------------------------\n");
    for (i=0; bb_list[i]; i++) {
	bb = bb_list[i];
	fprintf(stderr, "%d (%d)\t -> ", bb->index, bb->loop_depth);
	for (e=bb->succ_list; e != NULL; e=e->succ_next) {
	    fprintf(stderr, "%d ", e->to->index);
	}
	fprintf(stderr, "\t\t <- ");
	for (e=bb->pred_list; e != NULL; e=e->pred_next) {
	    fprintf(stderr, "%d ", e->from->index);
	}
	fprintf(stderr, "\n");
    }

    fprintf(stderr, "\n");

}

void dump_loops() {
    int i, j;
    Set *loop;

    fprintf(stderr, "Loop info\n---------\n");
    for (i = 0; i < n_loops; i++) {
        loop = loop_info[i]->loop;
        fprintf(stderr,
                "loop %d,  depth %d, size %d, entry %d, contains blocks:\n",
                i, loop_info[i]->depth,
                loop_info[i]->size, loop_info[i]->entry);
        for (j = 0; j < n_basic_blocks; j++)
            if (set_contains(loop, j))
                fprintf(stderr, "%d ", j);
        fprintf(stderr, "\n");
    }
}


void dump_symreg() {
    int i;

    if (!reglist)
        return;
    fprintf(stderr,
            "\nSymbols:\n----------------------------------------------\n");
    fprintf(stderr, "name\tfirst\tlast\t1.blk\t-blk\tset col tscore\t"
            "used\tlhs_use\tus flgs\n"
            "----------------------------------------------\n");
    for(i = 0; i <n_symbols; i++) {
        SymReg * r = reglist[i];
        if(!(r->type & VTREGISTER))
            continue;
        if(!r->first_ins)
            continue;
        fprintf(stderr, "%s\t%d\t%d\t%d\t%d\t%c   %2d  %d\t%d\t%d\t%x\n",
                r->name,
		    r->first_ins->index, r->last_ins->index,
		    r->first_ins->bbindex, r->last_ins->bbindex,
		    r->set,
                r->color, r->score,
                r->use_count, r->lhs_use_count,
                r->usage
               );
    }
    fprintf(stderr, "\n");
    dump_liveness_status();

}

void dump_liveness_status() {
    int i;

    fprintf(stderr, "\nSymbols:\n--------------------------------------\n");
    for(i = 0; i <n_symbols; i++) {
        SymReg * r = reglist[i];
        if (r->type & VTREGISTER )
            dump_liveness_status_var(r);
    }
    fprintf(stderr, "\n");

}


void dump_liveness_status_var(SymReg* r) {
    int i;
    Life_range *l;

    fprintf(stderr, "\nSymbol %s:", r->name);
    if (r->life_info==NULL) return;
    for (i=0; i<n_basic_blocks; i++) {
        l = r->life_info[i];

	if (l->flags & LF_lv_all) {
		fprintf(stderr, "\n\t%i:ALL\t", i);
	}
	else if (l->flags & LF_lv_inside) {
            fprintf(stderr, "\n\t%i:INSIDE", i);
	}

        if (l->flags & LF_lv_in)
            fprintf(stderr, "\n\t%i: IN\t", i);
        else if (l->flags & LF_lv_out)
            fprintf(stderr, "\n\t%i: OUT\t", i);
        else if (l->first_ins)
            fprintf(stderr, "\n\t%i: INS\t", i);

	if(l->first_ins) {
            fprintf(stderr, "[%d,%d]\t", l->first_ins->index,
                    l->last_ins->index);
	}
    }
    fprintf(stderr, "\n");
}

void dump_interference_graph() {
    int x, y, cnt;
    SymReg *r;

    fprintf(stderr, "\nDumping the Interf. graph:"
            "\n-------------------------------\n");
    for (x = 0; x < n_symbols; x++) {

	if (!reglist[x]->first_ins) continue;

	fprintf(stderr, "%s\t -> ", reglist[x]->name);
	cnt = 0;

        for (y = 0; y < n_symbols; y++) {

	     r = interference_graph[x*n_symbols + y];
	     if ( r && !r->simplified) {
	        fprintf(stderr, "%s ", r->name);
		cnt++;
	     }
        }
        fprintf(stderr, "(%d)\n", cnt);
    }
    fprintf(stderr, "\n");
}

void dump_dominators() {
    int i, j;

    fprintf(stderr, "\nDumping the Dominators Tree:"
            "\n-------------------------------\n");
    for (i=0; i < n_basic_blocks; i++) {
	fprintf (stderr, "%d <- ", i);

	for(j=0; j < n_basic_blocks; j++) {
            if (set_contains(dominators[i], j)) {
		fprintf(stderr, " %d", j);
	    }
	}

	fprintf(stderr, "\n");
    }

    fprintf(stderr, "\n");
}

/*
 * Local variables:
 * c-indentation-style: bsd
 * c-basic-offset: 4
 * indent-tabs-mode: nil
 * End:
 *
 * vim: expandtab shiftwidth=4:
*/
