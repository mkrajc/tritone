package org.mech.tritone.main.cmd.export.si;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.mech.tritone.main.arg.Arguments;
import org.mech.tritone.main.cmd.AbstractStringInstrumentCommand;
import org.mech.tritone.main.cmd.CommandException;
import org.mech.tritone.main.cmd.CommandLineUtils;
import org.mech.tritone.music.utils.StringUtils;
import org.mech.tritone.render.Format;
import org.mech.tritone.render.RendererDispatcher;
import org.mech.tritone.render.RenderingContext;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ExportStringInstrumentCommand<C extends RenderingContext> extends AbstractStringInstrumentCommand {

	private final Format DEFAULT_FORMAT = Format.TAB;

	@Autowired
	private RendererDispatcher rendererDispatcher;

	protected abstract C createContext(CommandLine line, List<String> subList);

	@Override
	public void execute(final CommandLine commandLine, final List<String> subList) {
		rendererDispatcher.dispatchRender(prepareFormat(commandLine), CommandLineUtils.getWriter(),
				createContext(commandLine, subList));
	}

	private Format prepareFormat(final CommandLine commandLine) {
		final String fmtValue = commandLine.getOptionValue(Arguments.FORMAT);
		try {
			return (fmtValue == null) ? DEFAULT_FORMAT : Format.valueOf(fmtValue.toUpperCase());
		} catch (final Exception e) {
			throw new CommandException(String.format("Formant '%s' is not valid. Please select one from: %s", fmtValue,
					StringUtils.toString(Format.values()).toLowerCase()));
		}

	}

}
