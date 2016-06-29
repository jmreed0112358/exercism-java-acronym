package acronym;

import exceptions.NotImplementedException;

public class Acronym
{
	public static String generate( String rawInput ) {
		if ( rawInput == null ) {
			throw new NullPointerException();
		} else if ( rawInput.isEmpty( ) ) {
			return "";
		}
		
		String input = sanitize( rawInput );
		String[] tokens = input.split( "\\s+" );
		StringBuilder sb = new StringBuilder();
		
		for ( int i = 0 ; i < tokens.length ; i++ ) {
			if ( Character.isLetter( tokens[i].charAt( 0 ) ) ) {
				sb.append( getAcronymCharacter( tokens[i] ) );
			}
		}
		
		return sb.toString( ).toUpperCase( );
	}
	
	/**
	 * Reduces a word to its acronym part.  eg. (Foo -> F, PHP -> P, HyperText -> HT).
	 * If a word is already an acronym, no point in repeating it, grab the first Character.
	 * If a word is not an acronym, process it, taking every upper case character.
	 * @param word
	 * @return
	 */
	private static String getAcronymCharacter( String word ) {
		if ( word.isEmpty( ) ) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		if ( isAcronym( word ) ) {
			sb.append( word.charAt( 0 ) );
		} else {
			sb.append( word.charAt( 0 ) );
			for ( int i = 1 ; i < word.length( ) ; i++ ) {
				if (Character.isUpperCase( word.charAt( i ) ) ) {
					sb.append( word.charAt( i ) );
				}
			}
		}
		
		return sb.toString( );
	}
	
	/**
	 * Returns true if the given word is an acronym.
	 * For this function, an acronym is a word where every character is an upper case letter.
	 * @param word
	 * @return
	 */
	private static boolean isAcronym( String word ) {
		for ( int i = 0 ; i < word.length( ) ; i++ ) {
			if ( !Character.isUpperCase( word.charAt( i ) ) ) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Remove non-printable characters.
	 * @param rawInput
	 * @return
	 */
	private static String sanitize( String rawInput ) {
		StringBuilder sb = new StringBuilder();
		for ( int i = 0 ; i < rawInput.length( ) ; i++ ) {
			if ( !Character.isISOControl( rawInput.charAt( i ) ) ) {
				if ( Character.isLetter( rawInput.charAt( i ) ) ) {
					sb.append( rawInput.charAt( i ) );
				} else if ( rawInput.charAt( i ) == '-' || rawInput.charAt( i ) == ' ' ) {
					sb.append( " " );
				}
			}
		}
		
		return sb.toString( );
	}
}
