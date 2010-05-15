## XXX does not cover .includes of core .pasm files

NQP_STAGE0_DIR = ext/nqp-rx/src/stage0

$(LIBRARY_DIR)/Regex.pbc: $(NQP_STAGE0_DIR)/Regex-s0.pir $(PARROT)
	$(PARROT) -o $@ $(NQP_STAGE0_DIR)/Regex-s0.pir

$(LIBRARY_DIR)/HLL.pbc: $(NQP_STAGE0_DIR)/HLL-s0.pir $(PARROT)
	$(PARROT) -o $@ $(NQP_STAGE0_DIR)/HLL-s0.pir

$(LIBRARY_DIR)/P6Regex.pbc: $(NQP_STAGE0_DIR)/P6Regex-s0.pir $(PARROT)
	$(PARROT) -o $@ $(NQP_STAGE0_DIR)/P6Regex-s0.pir

$(LIBRARY_DIR)/nqp-rx.pbc: $(NQP_STAGE0_DIR)/NQP-s0.pir $(PARROT)
	$(PARROT) -o $@ $(NQP_STAGE0_DIR)/NQP-s0.pir

## eventually nqp should be able to generate .pbc files directly
$(LIBRARY_DIR)/nqp-setting.pbc: $(NQP_STAGE0_DIR)/nqp-setting.pm $(LIBRARY_DIR)/nqp-rx.pbc $(NQPRX_LIB_PBCS)
	$(PARROT) $(LIBRARY_DIR)/nqp-rx.pbc --target=pir -o $(NQP_STAGE0_DIR)/nqp-setting.pir $(NQP_STAGE0_DIR)/nqp-setting.pm
	$(PARROT) -o $@ $(NQP_STAGE0_DIR)/nqp-setting.pir

ext/nqp-rx/src/gen/settings.pir: ext/nqp-rx/src/gen/settings.pm parrot-nqp.pbc
	$(PARROT) parrot-nqp.pbc --target=pir -o $@ ext/nqp-rx/src/gen/settings.pm

$(LIBRARY_DIR)/nqp-settings.pbc: ext/nqp-rx/src/gen/settings.pir $(PARROT)
	$(PARROT) -o $@ ext/nqp-rx/src/gen/settings.pir

## TT #1398 - pbc_to_exe cannot generate a specified target file
parrot-nqp.pbc : $(LIBRARY_DIR)/nqp-rx.pbc
	$(CP) $(LIBRARY_DIR)/nqp-rx.pbc $@

$(NQP_RX) : $(NQPRX_LIB_PBCS) $(PBC_TO_EXE) parrot-nqp.pbc
	$(PBC_TO_EXE) parrot-nqp.pbc

$(INSTALLABLENQP) : $(NQPRX_LIB_PBCS) src/install_config$(O) $(PBC_TO_EXE) parrot-nqp.pbc
	$(PBC_TO_EXE) parrot-nqp.pbc --install



